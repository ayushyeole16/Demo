import { createContext, useContext, useState, useEffect } from 'react';
import api from '../services/api';

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        // Check if user is logged in (e.g., check token in localStorage)
        const token = localStorage.getItem('token');
        if (token) {
            // Optimistically set user or fetch profile
            // For now, let's just assume valid if token exists (improve later)
            const storedUser = JSON.parse(localStorage.getItem('user'));
            if (storedUser) setUser(storedUser);
        }
        setLoading(false);
    }, []);

    const login = async (username, password) => {
        try {
            // TODO: Adjust endpoint based on User Service
            // Assuming User Service has /users/login or similar. 
            // The gateway routes /users/** to user-service.
            // Let's assume user-service has a LoginController or similar.
            const response = await api.post('/users/login', { username, password });
            const { token, ...userData } = response.data;

            localStorage.setItem('token', token);
            localStorage.setItem('user', JSON.stringify(userData));
            setUser(userData);
            return true;
        } catch (error) {
            console.error("Login failed", error);
            return false;
        }
    };

    const register = async (userData) => {
        try {
            await api.post('/users/register', userData);
            return true;
        } catch (error) {
            console.error("Registration failed", error);
            throw error;
        }
    }

    const logout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        setUser(null);
    };

    return (
        <AuthContext.Provider value={{ user, login, logout, register, loading }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => useContext(AuthContext);

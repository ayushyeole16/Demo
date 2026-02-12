import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { ShoppingCart, User, Menu, X, LogOut, Search } from 'lucide-react';
import { useAuth } from '../context/AuthContext';
import { useCart } from '../context/CartContext';

const Header = () => {
    const { user, logout } = useAuth();
    const { cart } = useCart();
    const navigate = useNavigate();
    const [isMenuOpen, setIsMenuOpen] = useState(false);
    const [searchTerm, setSearchTerm] = useState('');

    const cartCount = cart.reduce((acc, item) => acc + item.quantity, 0);

    const handleSearch = (e) => {
        e.preventDefault();
        if (searchTerm.trim()) {
            navigate(`/?search=${searchTerm}`);
            setIsMenuOpen(false);
        }
    };

    return (
        <header className="bg-white shadow-md sticky top-0 z-50">
            <div className="container mx-auto px-4 py-4 flex justify-between items-center">
                {/* Logo */}
                <Link to="/" className="text-2xl font-bold text-primary">
                    ShopEase
                </Link>

                {/* Desktop Search */}
                <form onSubmit={handleSearch} className="hidden md:flex flex-1 max-w-lg mx-8 relative">
                    <input
                        type="text"
                        placeholder="Search products..."
                        className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-primary"
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                    />
                    <button type="submit" className="absolute right-3 top-2.5 text-gray-500 hover:text-primary">
                        <Search size={20} />
                    </button>
                </form>

                {/* Desktop Navigation */}
                <nav className="hidden md:flex items-center space-x-6">
                    <Link to="/" className="text-gray-600 hover:text-primary font-medium">Home</Link>
                    <Link to="/products" className="text-gray-600 hover:text-primary font-medium">Products</Link>

                    {user ? (
                        <div className="relative group">
                            <button className="flex items-center space-x-1 text-gray-600 hover:text-primary">
                                <User size={20} />
                                <span>{user.username}</span>
                            </button>
                            <div className="absolute right-0 mt-2 w-48 bg-white border rounded-lg shadow-lg py-2 hidden group-hover:block">
                                <Link to="/orders" className="block px-4 py-2 hover:bg-gray-100">My Orders</Link>
                                <button onClick={logout} className="w-full text-left px-4 py-2 hover:bg-gray-100 text-red-500 flex items-center">
                                    <LogOut size={16} className="mr-2" /> Logout
                                </button>
                            </div>
                        </div>
                    ) : (
                        <Link to="/login" className="text-gray-600 hover:text-primary font-medium">Login</Link>
                    )}

                    <Link to="/cart" className="relative text-gray-600 hover:text-primary">
                        <ShoppingCart size={24} />
                        {cartCount > 0 && (
                            <span className="absolute -top-2 -right-2 bg-red-500 text-white text-xs font-bold rounded-full w-5 h-5 flex items-center justify-center">
                                {cartCount}
                            </span>
                        )}
                    </Link>
                </nav>

                {/* Mobile Menu Button */}
                <button className="md:hidden text-gray-600" onClick={() => setIsMenuOpen(!isMenuOpen)}>
                    {isMenuOpen ? <X size={24} /> : <Menu size={24} />}
                </button>
            </div>

            {/* Mobile Menu */}
            {isMenuOpen && (
                <div className="md:hidden bg-white border-t p-4">
                    <form onSubmit={handleSearch} className="flex mb-4">
                        <input
                            type="text"
                            placeholder="Search..."
                            className="w-full px-4 py-2 border rounded-l-lg focus:outline-none"
                            value={searchTerm}
                            onChange={(e) => setSearchTerm(e.target.value)}
                        />
                        <button type="submit" className="bg-primary text-white px-4 rounded-r-lg">
                            <Search size={20} />
                        </button>
                    </form>
                    <div className="flex flex-col space-y-4">
                        <Link to="/" className="text-gray-600 font-medium" onClick={() => setIsMenuOpen(false)}>Home</Link>
                        <Link to="/products" className="text-gray-600 font-medium" onClick={() => setIsMenuOpen(false)}>Products</Link>
                        <Link to="/cart" className="flex items-center text-gray-600 font-medium" onClick={() => setIsMenuOpen(false)}>
                            <ShoppingCart size={20} className="mr-2" /> Cart ({cartCount})
                        </Link>
                        {user ? (
                            <>
                                <Link to="/orders" className="text-gray-600 font-medium" onClick={() => setIsMenuOpen(false)}>My Orders</Link>
                                <button onClick={() => { logout(); setIsMenuOpen(false); }} className="text-red-500 font-medium text-left">Logout</button>
                            </>
                        ) : (
                            <Link to="/login" className="text-gray-600 font-medium" onClick={() => setIsMenuOpen(false)}>Login</Link>
                        )}
                    </div>
                </div>
            )}
        </header>
    );
};

export default Header;

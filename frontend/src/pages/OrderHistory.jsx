import React, { useEffect, useState } from 'react';
import api from '../services/api';
import { useAuth } from '../context/AuthContext';
import { Loader } from 'lucide-react';

const OrderHistory = () => {
    const { user } = useAuth();
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (!user) return;

        const fetchOrders = async () => {
            try {
                // Endpoint to get orders by user. 
                // Based on OrderController: @GetMapping("/user/{userId}")
                // The gateway routes /orders/** to order-service.
                // So full path is /orders/user/{userId}
                const response = await api.get(`/orders/user/${user.id}`);
                setOrders(response.data);
            } catch (err) {
                console.error("Failed to fetch orders", err);
                setError("Failed to load order history.");
            } finally {
                setLoading(false);
            }
        };

        fetchOrders();
    }, [user]);

    if (!user) return <div className="text-center mt-10">Please login to view your orders.</div>;
    if (loading) return <div className="flex justify-center items-center h-screen"><Loader className="animate-spin text-primary" size={48} /></div>;
    if (error) return <div className="text-center text-red-500 mt-10">{error}</div>;

    return (
        <div className="container mx-auto px-4 py-8">
            <h1 className="text-3xl font-bold mb-8">My Orders</h1>
            {orders.length === 0 ? (
                <div className="text-gray-600">You have no past orders.</div>
            ) : (
                <div className="space-y-6">
                    {orders.map(order => (
                        <div key={order.id} className="bg-white rounded-lg shadow p-6 border border-gray-100">
                            <div className="flex justify-between items-center mb-4 border-b pb-2">
                                <div>
                                    <span className="font-bold text-lg">Order #{order.id}</span>
                                    <span className="text-gray-500 text-sm ml-4">{new Date(order.orderTime).toLocaleDateString()}</span>
                                </div>
                                <span className={`px-3 py-1 rounded-full text-xs font-bold ${order.status === 'COMPLETED' ? 'bg-green-100 text-green-800' :
                                        order.status === 'PENDING' ? 'bg-yellow-100 text-yellow-800' :
                                            'bg-red-100 text-red-800'
                                    }`}>
                                    {order.status}
                                </span>
                            </div>
                            <div className="flex justify-between items-center">
                                <div>
                                    {/* Ideally we would fetch product details here or backend sends product name */}
                                    {/* For now just showing Product ID and Quantity */}
                                    <p className="text-gray-700">Product ID: {order.productId}</p>
                                    <p className="text-gray-700">Quantity: {order.quantity}</p>
                                </div>
                                <div className="text-xl font-bold text-primary">
                                    ${order.totalPrice}
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default OrderHistory;

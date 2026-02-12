import React, { useState } from 'react';
import { useCart } from '../context/CartContext';
import { useAuth } from '../context/AuthContext';
import api from '../services/api';
import { useNavigate } from 'react-router-dom';

const Checkout = () => {
    const { cart, total, clearCart } = useCart();
    const { user } = useAuth();
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const [address, setAddress] = useState('');

    const handleCheckout = async (e) => {
        e.preventDefault();
        setLoading(true);
        setError('');

        if (!user) {
            setError('Please login to checkout.');
            setLoading(false);
            return;
        }

        try {
            // Logic to create order
            // Sending { userId, items: [...], total }
            // Since backend expects one Order object, and the Order entity has ONE productId and Quantity.
            // This suggests the current backend Order model supports only ONE product per order?
            // Checking Order.java: 
            // private Long productId;
            // private Integer quantity;
            // YES. The current backend model is simplified (1 order = 1 product type).
            // To support multiple items, I would need to send multiple requests or update the backend.
            // For now, I'll loop through cart items and place individual orders.

            const promises = cart.map(item => {
                const orderData = {
                    userId: user.id, // Assuming user object has id
                    productId: item.id,
                    quantity: item.quantity,
                    totalPrice: item.price * item.quantity,
                    status: 'PENDING'
                };
                return api.post('/orders/place', orderData);
            });

            await Promise.all(promises);

            clearCart();
            alert('Order placed successfully!');
            navigate('/orders');
        } catch (err) {
            console.error(err);
            setError('Failed to place order. Please try again.');
        } finally {
            setLoading(false);
        }
    };

    if (cart.length === 0) {
        navigate('/cart');
        return null;
    }

    return (
        <div className="container mx-auto px-4 py-8">
            <h1 className="text-3xl font-bold mb-8">Checkout</h1>
            <div className="flex flex-col md:flex-row gap-8">
                <div className="flex-1 bg-white p-6 rounded-lg shadow">
                    <h2 className="text-xl font-bold mb-4">Shipping Information</h2>
                    {error && <div className="text-red-500 mb-4">{error}</div>}
                    <form onSubmit={handleCheckout}>
                        <div className="mb-4">
                            <label className="block text-gray-700 mb-2">Full Name</label>
                            <input type="text" value={user?.username || ''} disabled className="w-full px-4 py-2 border rounded bg-gray-50" />
                        </div>
                        <div className="mb-4">
                            <label className="block text-gray-700 mb-2">Address</label>
                            <textarea
                                className="w-full px-4 py-2 border rounded focus:ring-2 focus:ring-primary"
                                rows="3"
                                value={address}
                                onChange={(e) => setAddress(e.target.value)}
                                required
                            ></textarea>
                        </div>
                        <div className="mb-4">
                            <label className="block text-gray-700 mb-2">Payment Method</label>
                            <select className="w-full px-4 py-2 border rounded">
                                <option>Credit Card (Mock)</option>
                                <option>Cash on Delivery</option>
                            </select>
                        </div>

                        <button
                            type="submit"
                            disabled={loading}
                            className={`w-full bg-green-500 text-white py-3 rounded-lg font-bold hover:bg-green-600 transition-colors ${loading ? 'opacity-50 cursor-not-allowed' : ''}`}
                        >
                            {loading ? 'Processing...' : `Pay $${total.toFixed(2)}`}
                        </button>
                    </form>
                </div>

                <div className="w-full md:w-80 bg-gray-50 p-6 rounded-lg h-fit">
                    <h3 className="font-bold mb-4">Your Items</h3>
                    <div className="space-y-4">
                        {cart.map(item => (
                            <div key={item.id} className="flex justify-between text-sm">
                                <span>{item.name} x {item.quantity}</span>
                                <span className="font-medium">${(item.price * item.quantity).toFixed(2)}</span>
                            </div>
                        ))}
                        <div className="border-t pt-4 flex justify-between font-bold">
                            <span>Total</span>
                            <span>${total.toFixed(2)}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Checkout;

import React from 'react';
import { useCart } from '../context/CartContext';
import { Trash2, Plus, Minus } from 'lucide-react';
import { Link } from 'react-router-dom';

const Cart = () => {
    const { cart, removeFromCart, updateQuantity, total } = useCart();

    if (cart.length === 0) {
        return (
            <div className="container mx-auto px-4 py-20 text-center">
                <h2 className="text-3xl font-bold mb-4">Your Cart is Empty</h2>
                <p className="text-gray-600 mb-8">Looks like you haven't added anything to your cart yet.</p>
                <Link to="/" className="bg-primary text-white px-6 py-3 rounded-lg hover:bg-indigo-700 transition-colors">
                    Start Shopping
                </Link>
            </div>
        );
    }

    return (
        <div className="container mx-auto px-4 py-8">
            <h1 className="text-3xl font-bold mb-8">Shopping Cart</h1>
            <div className="flex flex-col lg:flex-row gap-8">
                {/* Cart Items */}
                <div className="flex-1">
                    <div className="bg-white rounded-lg shadow overflow-hidden">
                        {cart.map((item) => (
                            <div key={item.id} className="flex items-center p-6 border-b border-gray-100 last:border-b-0">
                                <img
                                    src={item.img}
                                    alt={item.name}
                                    className="w-20 h-20 object-cover rounded-md mr-6"
                                    onError={(e) => { e.target.src = 'https://via.placeholder.com/100' }}
                                />
                                <div className="flex-1">
                                    <h3 className="text-lg font-semibold text-gray-800">{item.name}</h3>
                                    <p className="text-gray-500 text-sm">{item.category}</p>
                                    <p className="text-primary font-bold mt-1">${item.price}</p>
                                </div>
                                <div className="flex items-center space-x-4">
                                    <div className="flex items-center border border-gray-300 rounded">
                                        <button
                                            onClick={() => updateQuantity(item.id, item.quantity - 1)}
                                            className="px-3 py-1 bg-gray-50 hover:bg-gray-200"
                                        >
                                            <Minus size={16} />
                                        </button>
                                        <span className="px-3">{item.quantity}</span>
                                        <button
                                            onClick={() => updateQuantity(item.id, item.quantity + 1)}
                                            className="px-3 py-1 bg-gray-50 hover:bg-gray-200"
                                        >
                                            <Plus size={16} />
                                        </button>
                                    </div>
                                    <button
                                        onClick={() => removeFromCart(item.id)}
                                        className="text-red-500 hover:text-red-700"
                                    >
                                        <Trash2 size={20} />
                                    </button>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>

                {/* Order Summary */}
                <div className="w-full lg:w-96">
                    <div className="bg-white rounded-lg shadow p-6">
                        <h2 className="text-xl font-bold mb-6">Order Summary</h2>
                        <div className="flex justify-between mb-4 text-gray-600">
                            <span>Subtotal</span>
                            <span>${total.toFixed(2)}</span>
                        </div>
                        <div className="flex justify-between mb-4 text-gray-600">
                            <span>Shipping</span>
                            <span>Free</span>
                        </div>
                        <div className="border-t pt-4 flex justify-between font-bold text-lg mb-6">
                            <span>Total</span>
                            <span>${total.toFixed(2)}</span>
                        </div>
                        <Link
                            to="/checkout"
                            className="block w-full bg-primary text-white text-center py-3 rounded-lg hover:bg-indigo-700 transition-colors font-bold"
                        >
                            Proceed to Checkout
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Cart;

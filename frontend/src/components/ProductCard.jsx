import React from 'react';
import { Link } from 'react-router-dom';
import { ShoppingCart } from 'lucide-react';
import { useCart } from '../context/CartContext';

const ProductCard = ({ product }) => {
    const { addToCart } = useCart();

    return (
        <div className="bg-white rounded-xl shadow-md overflow-hidden hover:shadow-lg transition-shadow duration-300">
            <Link to={`/products/${product.id}`}>
                <div className="h-48 overflow-hidden bg-gray-100 flex items-center justify-center">
                    {product.img ? (
                        <img
                            src={product.img}
                            alt={product.name}
                            className="w-full h-full object-cover"
                            onError={(e) => { e.target.src = 'https://via.placeholder.com/300?text=No+Image' }}
                        />
                    ) : (
                        <span className="text-gray-400">No Image</span>
                    )}
                </div>
            </Link>
            <div className="p-4">
                <Link to={`/products/${product.id}`}>
                    <h3 className="text-lg font-semibold text-gray-800 hover:text-primary truncate">{product.name}</h3>
                </Link>
                <div className="flex justify-between items-center mt-2">
                    <span className="text-xl font-bold text-primary">${product.price}</span>
                    <span className="text-xs text-gray-500 bg-gray-100 px-2 py-1 rounded">{product.category}</span>
                </div>
                <button
                    onClick={() => addToCart(product)}
                    className="w-full mt-4 bg-dark text-white py-2 rounded-lg flex items-center justify-center hover:bg-primary transition-colors"
                >
                    <ShoppingCart size={18} className="mr-2" /> Add to Cart
                </button>
            </div>
        </div>
    );
};

export default ProductCard;

import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import api from '../services/api';
import { useCart } from '../context/CartContext';
import { ShoppingCart, Loader } from 'lucide-react';

const ProductDetails = () => {
    const { id } = useParams();
    const [product, setProduct] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const { addToCart } = useCart();

    useEffect(() => {
        const fetchProduct = async () => {
            try {
                const response = await api.get(`/api/products/${id}`);
                setProduct(response.data);
            } catch (err) {
                console.error("Failed to load product", err);
                setError("Product not found or failed to load.");
            } finally {
                setLoading(false);
            }
        };
        fetchProduct();
    }, [id]);

    if (loading) return <div className="flex justify-center items-center h-screen"><Loader className="animate-spin text-primary" size={48} /></div>;
    if (error) return <div className="text-center text-red-500 mt-10">{error}</div>;
    if (!product) return <div className="text-center mt-10">Product not found.</div>;

    return (
        <div className="container mx-auto px-4 py-8">
            <div className="flex flex-col md:flex-row gap-8">
                <div className="w-full md:w-1/2 bg-gray-100 rounded-lg overflow-hidden flex items-center justify-center p-4">
                    {product.img ? (
                        <img
                            src={product.img}
                            alt={product.name}
                            className="max-h-96 object-contain"
                            onError={(e) => { e.target.src = 'https://via.placeholder.com/500?text=No+Image' }}
                        />
                    ) : (
                        <span className="text-gray-400">No Image</span>
                    )}
                </div>
                <div className="w-full md:w-1/2">
                    <div className="text-sm text-gray-500 mb-2">{product.category} {product.subCategory && `> ${product.subCategory}`}</div>
                    <h1 className="text-3xl font-bold text-gray-800 mb-4">{product.name}</h1>
                    <p className="text-2xl font-bold text-primary mb-6">${product.price}</p>
                    <p className="text-gray-600 mb-8 leading-relaxed">
                        {product.description || "No description available for this product."}
                    </p>
                    <div className="flex items-center space-x-4">
                        <button
                            onClick={() => addToCart(product)}
                            className="flex-1 bg-dark text-white py-3 rounded-lg flex items-center justify-center hover:bg-primary transition-colors font-bold"
                        >
                            <ShoppingCart size={20} className="mr-2" /> Add to Cart
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ProductDetails;

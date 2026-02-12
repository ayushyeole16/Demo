import React from 'react';
import { Facebook, Twitter, Instagram, Mail, Phone, MapPin } from 'lucide-react';

const Footer = () => {
    return (
        <footer className="bg-dark text-white pt-10 pb-6">
            <div className="container mx-auto px-4 grid grid-cols-1 md:grid-cols-4 gap-8">
                {/* About */}
                <div>
                    <h3 className="text-xl font-bold mb-4">ShopEase</h3>
                    <p className="text-gray-400 text-sm">
                        Your one-stop shop for everything you need. Premium quality products at the best prices.
                    </p>
                </div>

                {/* Quick Links */}
                <div>
                    <h3 className="text-lg font-bold mb-4">Quick Links</h3>
                    <ul className="space-y-2 text-gray-400 text-sm">
                        <li><a href="/" className="hover:text-secondary">Home</a></li>
                        <li><a href="/products" className="hover:text-secondary">Shop</a></li>
                        <li><a href="/cart" className="hover:text-secondary">Cart</a></li>
                        <li><a href="/orders" className="hover:text-secondary">My Orders</a></li>
                    </ul>
                </div>

                {/* Contact */}
                <div>
                    <h3 className="text-lg font-bold mb-4">Contact Us</h3>
                    <ul className="space-y-2 text-gray-400 text-sm">
                        <li className="flex items-center"><MapPin size={16} className="mr-2" /> 123 Fashion St, New York</li>
                        <li className="flex items-center"><Phone size={16} className="mr-2" /> +1 234 567 890</li>
                        <li className="flex items-center"><Mail size={16} className="mr-2" /> support@shopease.com</li>
                    </ul>
                </div>

                {/* Socials */}
                <div>
                    <h3 className="text-lg font-bold mb-4">Follow Us</h3>
                    <div className="flex space-x-4">
                        <a href="#" className="hover:text-secondary"><Facebook /></a>
                        <a href="#" className="hover:text-secondary"><Twitter /></a>
                        <a href="#" className="hover:text-secondary"><Instagram /></a>
                    </div>
                </div>
            </div>
            <div className="text-center text-gray-500 text-sm mt-8 border-t border-gray-700 pt-4">
                &copy; 2024 ShopEase. All rights reserved.
            </div>
        </footer>
    );
};

export default Footer;

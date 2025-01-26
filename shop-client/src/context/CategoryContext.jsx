import React, { createContext, useState } from 'react';

export const CategoryContext = createContext();

export const CategoryProvider = ({ children }) => {
    const [categories, setCategories] = useState([]);

    const addCategory = (category) => {
        setCategories([...categories, category]);
    };

    const updateCategory = (id, updatedCategory) => {
        setCategories(categories.map(cat => cat.id === id ? updatedCategory : cat));
    };

    const deleteCategory = (id) => {
        setCategories(categories.filter(cat => cat.id !== id));
    };

    return (
        <CategoryContext.Provider value={{ categories, addCategory, updateCategory, deleteCategory }}>
            {children}
        </CategoryContext.Provider>
    );
};

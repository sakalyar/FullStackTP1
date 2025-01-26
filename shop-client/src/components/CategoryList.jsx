import React, { useContext } from 'react';
import { CategoryContext } from '../context/CategoryContext';

const CategoryList = () => {
    const { categories, deleteCategory } = useContext(CategoryContext);

    return (
        <ul>
            {categories.map((category) => (
                <li key={category.id}>
                    {category.name} - {category.createdAt}
                    <button onClick={() => deleteCategory(category.id)}>Supprimer</button>
                </li>
            ))}
        </ul>
    );
};

export default CategoryList;

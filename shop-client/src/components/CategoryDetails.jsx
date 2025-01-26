import React, { useContext } from 'react';
import { CategoryContext } from '../context/CategoryContext';

const CategoryDetails = ({ id }) => {
    const { categories } = useContext(CategoryContext);
    const category = categories.find(cat => cat.id === id);

    if (!category) return <div>Catégorie non trouvée</div>;

    return (
        <div>
            <h3>Détails de la catégorie</h3>
            <p><strong>Nom : </strong>{category.name}</p>
            <p><strong>Catégorie parent : </strong>{category.parent}</p>
            <p><strong>Date de création : </strong>{category.createdAt}</p>
        </div>
    );
};

export default CategoryDetails;

import React, { useState, useContext } from 'react';
import { CategoryContext } from '../context/CategoryContext';

const CategoryForm = () => {
    const { addCategory } = useContext(CategoryContext); // Accès à la fonction de gestion des catégories
    const [name, setName] = useState('');
    const [parent, setParent] = useState('');

    // Gestion de l'envoi du formulaire
    const handleSubmit = (e) => {
        e.preventDefault();
        addCategory({ id: Date.now(), name, parent, createdAt: new Date().toISOString() });
        setName('');
        setParent('');
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                placeholder="Nom de la catégorie"
                value={name}
                onChange={(e) => setName(e.target.value)}
                required
            />
            <input
                type="text"
                placeholder="Catégorie parent"
                value={parent}
                onChange={(e) => setParent(e.target.value)}
            />
            <button type="submit">Ajouter</button>
        </form>
    );
};

export default CategoryForm;

import React, { useState } from 'react';
import { CategoryProvider } from './context/CategoryContext';
import CategoryForm from './components/CategoryForm';
import CategoryList from './components/CategoryList';
import CategoryDetails from './components/CategoryDetails';

const App = () => {
    const [selectedCategoryId, setSelectedCategoryId] = useState(null);

    return (
        <CategoryProvider>
            <h1>Gestion des Catégories</h1>
            <CategoryForm />
            <CategoryList />
            {selectedCategoryId && <CategoryDetails id={selectedCategoryId} />}
        </CategoryProvider>
    );
};

export default App;

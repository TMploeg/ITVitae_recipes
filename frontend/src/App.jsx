import { Route, Routes } from 'react-router-dom';
import './App.css';
import AddRecipe from './pages/add-recipe/AddRecipe';
import Home from './pages/home';
import Ingredients from './pages/ingredients';
import Recipe from './pages/recipe';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/ingredients" element={<Ingredients />} />
      <Route path="/recipe/:id" element={<Recipe />} />
      <Route path="/add-recipe" element={<AddRecipe />} />
    </Routes>
  )
}

export default App

export const API_URL = 'http://localhost:8080/api/v1/';

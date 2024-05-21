import { Route, Routes, useNavigate } from 'react-router-dom';
import './App.css';
import AddRecipe from './pages/add-recipe/AddRecipe';
import Home from './pages/home';
import Ingredients from './pages/ingredients';
import Recipe from './pages/recipe';

function App() {
  const navigate = useNavigate();

  return (
    <div>
      <div className="toolbar">
        <div onClick={() => navigate('/')}>Home</div>
        <div onClick={() => navigate('/ingredients')}>Ingredients</div>
      </div>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/ingredients" element={<Ingredients />} />
        <Route path="/recipe/:id" element={<Recipe />} />
        <Route path="/add-recipe" element={<AddRecipe />} />
      </Routes>
    </div>
  )
}

export default App

export const API_URL = 'http://localhost:8080/api/v1/';

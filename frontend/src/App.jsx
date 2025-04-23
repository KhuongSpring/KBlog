import './App.css'
import Login from "./components/auth/login.jsx";
import Home from './components/home/home.jsx';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';

function App() {
    return (
        <div>
            <Login/>
        </div>,
            <Router>
                <Routes>
                    <Route path="/" element={<Login/>}/>
                    <Route path="/home" element={<Home/>}/>
                </Routes>
            </Router>
    )
}

export default App

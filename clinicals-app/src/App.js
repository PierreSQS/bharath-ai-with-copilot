import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Routes } from 'react-router-dom';
import AddPatient from './components/AddPatient';
import AddClinicals from './components/AddClinicals';
import Home from './components/Home';
import { Route } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';



function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/addPatient" element={<AddPatient />} />
        <Route path="/addClinicals/:patientId" element={<AddClinicals />} />
      </Routes>
      </BrowserRouter>
      <ToastContainer autoClose={2000} position=''/>
    </div>
  );
}

export default App;

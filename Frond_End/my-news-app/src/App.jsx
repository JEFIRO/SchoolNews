
import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./page/Home/home"
import Te from"./content/UserManagement/UserManagement"
import UserDetail from"./content/UserDetail/userDetail"
const App = () => {
  return (
    <Router>
        <Routes>
          <Route path="/" element={<Te />} />
          <Route path="/user/:id" element={<UserDetail />} />
          </Routes>
    </Router>
  );
};

export default App;

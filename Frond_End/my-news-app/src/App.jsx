import { useState } from "react";
import "./App.css";
import Header from "./content/header/header";
import Home from "./content/homeBody/Home"

function App() {
  const [count, setCount] = useState(0);

  return <>
    <Home/>
  </>;
}

export default App;

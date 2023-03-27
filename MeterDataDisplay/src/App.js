import React from "react";
import { Route, Routes } from "react-router-dom";
import Layout from "./Components/Layout/Layout";
import NotFound from "./NotFound404";
import EnergyRegister from "./Components/Tables/EnergyRegister";
import NoLoad from './Components/Tables/NoLoad'
import MeterTest from './Components/Tables/MeterTest'
import TestBase from "./Components/Tables/TestBase";
import CalibrationTest from "./Components/Tables/CalibrationTest";
import Home from './Components/Home/Home'


function App() {
  return (
  <>
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route path='/' element={<Home />} />
        <Route path="EnergyRegister" element={<EnergyRegister />} />
        <Route path="TestBase" element={<TestBase />} />
        <Route path="NoLoad" element={<NoLoad />} />
        <Route path="MeterTest" element={<MeterTest />} />
        <Route path="CalibrationTest" element={<CalibrationTest />} />
      </Route>

      <Route path="*" element={<NotFound />} />
    </Routes>
  </>
  );
}

export default App;

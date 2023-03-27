import React from "react";
import {Link, useLocation} from 'react-router-dom'

const Navbar = () => {
    const { pathname } = useLocation();

    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark p-4">
            <div className="container-fluid">
                <Link className=" fs-3 navbar-brand" to='/'>Pdf Extractor</Link>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div className="navbar-nav">
                    <Link className={pathname === "/TestBase" ? "fs-4 nav-link active": "fs-4 nav-link"} to='/TestBase'>TestBase</Link>
                    <Link className={pathname === "/EnergyRegister" ? "fs-4 nav-link active": "fs-4 nav-link"} to='/EnergyRegister'>EnergyRegisterTest</Link>
                    <Link className={pathname === "/NoLoad" ? "fs-4 nav-link active": "fs-4 nav-link"} to='/NoLoad'>NoLoad</Link>
                    <Link className={pathname === "/MeterTest" ? "fs-4 nav-link active": "fs-4 nav-link"} to='/MeterTest'>MeterTest</Link>
                    <Link className={pathname === "/CalibrationTest" ? "fs-4 nav-link active": "fs-4 nav-link"} to='/CalibrationTest'>CalibrationTest</Link>
                </div>
                </div>
            </div>
            </nav>
        </div>
    )
}

export default Navbar
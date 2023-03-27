import React from "react";
import { Outlet } from "react-router-dom";
import Navbar from "../Navbar/Navbar";

const Layout = () => {
    return(
        <div>
            <Navbar />
            <div className="mt-5 container">
             {<Outlet />}
            </div>
        </div>
    )
}

export default Layout
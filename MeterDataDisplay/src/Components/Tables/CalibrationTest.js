import React, {useEffect, useState} from "react";

const CalibrationTest = () => {

  const [requestdata, setRequestdata] = useState([])
  const [waiting, setWaiting] =  useState(true)

  useEffect(() => {
    const datafetch = async () => {
      const response = await fetch("http://localhost:8080/CalibrationTest", {
        headers: {
          "Content-Type": "application/json",
        },
      });
      const data = await response.json();
      setRequestdata(data)
      setWaiting(false)
      console.log(data);
    };
    datafetch();
  }, []);

    return(

        <div className="mx-5">
         <h1>Calibration Test</h1>
          <table className="table table-hover">
          <thead className="table-dark">
            <tr>
              <th scope="col">hjid</th>
              <th scope="col">calibrationState</th>
              <th scope="col">CURRENT_RATIO</th>
              <th scope="col">ENERGY_LO_VALUE</th>
              <th scope="col">ENERGY_HI_VALUE</th>
              <th scope="col">GAIN_CORR</th>
              <th scope="col">PowerOffset</th>
              <th scope="col">Phase_Correction</th>
              <th scope="col">STARTING_CURRENT_INT</th>
              <th scope="col">STARTING_CURRENT_FRAC</th>
              <th scope="col">VOLTAGE_RATIO</th>
            </tr>
          </thead>
          <tbody>
            {waiting && ( <div className="container h-100 d-flex justify-content-center m-5 pl-5 ">
            <div className="text-xl-center">
  <div className="spinner-border" role="status">
    <span className="visually-hidden">Loading...</span>
  </div>
</div>
</div>
)}
        {!waiting && requestdata.length > 0 && 
          requestdata.map((data) => 
              <tr key={data.hjid} >
              <td>{data.hjid}</td>
              <td>{data.calibrationState}</td>
              <td>{data.cr}</td>
              <td>{data.elo}</td>
              <td>{data.ehi}</td>
              <td>{data.gco}</td>
              <td>{data.stCint}</td>
              <td>{data.stCfra}</td>
              <td>{data.vr}</td>
            </tr>
           
         )}
          </tbody>
          </table>
      </div>
      
    )
}

export default CalibrationTest
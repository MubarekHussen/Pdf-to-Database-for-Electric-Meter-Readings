import React, {useEffect, useState} from "react";

const EnergyRegister = () => {

  const [requestdata, setRequestdata] = useState([])
  const [waiting, setWaiting] =  useState(true)

  useEffect(() => {
    const datafetch = async () => {
      const response = await fetch("http://localhost:8080/EnergyRegisterTest", {
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

        <div>
         <h1>Energy Register Test</h1>
          <table className="table table-hover">
          <thead className="table-dark">
            <tr>
              <th scope="col">hjid</th>
              <th scope="col">endEnergy</th>
              <th scope="col">dosageEnergy</th>
              <th scope="col">percentError</th>
              <th scope="col">startEnergy</th>
              <th scope="col">evaluation</th>
            </tr>
          </thead>
          <tbody>
          {waiting && ( <div class="container h-100 d-flex justify-content-center m-5 pl-5 ">
            <div class="text-xl-center">
  <div class="spinner-border" role="status">
    <span class="visually-hidden">Loading...</span>
  </div>
</div>
</div>
)}
        {!waiting && requestdata.length > 0 && 
          requestdata.map((data) => 
              <tr key={data.hjid} >
              <td>{data.hjid}</td>
              <td>{data.endEnergy}</td>
              <td>{data.dosageEnergy}</td>
              <td>{data.percentError}</td>
              <td>{data.startEnergy}</td>
              <td>{data.evaluation}</td>
            </tr>
           
         )}
          </tbody>
          </table>
      </div>
      
    )
}

export default EnergyRegister
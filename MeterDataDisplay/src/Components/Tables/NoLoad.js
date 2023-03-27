import React, {useEffect, useState} from "react";

const NoLoad = () => {

  const [requestdata, setRequestdata] = useState([])
  const [waiting, setWaiting] =  useState(true)

  useEffect(() => {
    const datafetch = async () => {
      const response = await fetch("http://localhost:8080/NoLoad", {
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
         <h1>No Load Test</h1>
          <table className="table table-hover">
          <thead className="table-dark">
            <tr>
              <th scope="col">hjid</th>
              <th scope="col">edgesCountNoLoad</th>
              <th scope="col">alirTyp</th>
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
              <td>{data.edgesCountNoLoad}</td>
              <td>{data.alirTyp}</td>
              <td>{data.evaluation}</td>
            </tr>
           
         )}
          </tbody>
          </table>
      </div>
      
    )
}

export default NoLoad
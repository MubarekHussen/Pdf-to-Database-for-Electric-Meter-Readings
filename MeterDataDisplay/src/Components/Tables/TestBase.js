import React, {useEffect, useState} from "react";

const TestBase = () => {

  const [requestdata, setRequestdata] = useState([])
  const [waiting, setWaiting] =  useState(true)

  useEffect(() => {
    const datafetch = async () => {
      const response = await fetch("http://localhost:8080/TestBase", {
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
         <h1>Test Base</h1>
          <table className="table table-hover">
          <thead className="table-dark">
            <tr>
              <th scope="col">hjid</th>
              <th scope="col">meterPosition</th>
              <th scope="col">frontCoverSn</th>
              <th scope="col">sessionId</th>
              <th scope="col">report</th>
              <th scope="col">testStartTime</th>
              <th scope="col">testEndTime</th>
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
              <td>{data.meterPosition}</td>
              <td>{data.frontCoverSn}</td>
              <td>{data.sessionId}</td>
              <td>{data.report}</td>
              <td>{data.testStartTime}</td>
              <td>{data.testEndTime}</td>
              <td>{data.evaluation}</td>
            </tr>
           
         )}
          </tbody>
          </table>
      </div>
      
    )
}

export default TestBase
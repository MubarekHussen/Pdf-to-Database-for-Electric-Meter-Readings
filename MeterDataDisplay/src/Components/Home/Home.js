import React from "react";

const Home = () => {
    return(
        <>
  <div style={{backgroundColor:""}}class="row g-0">
    <div class="col">
        <div className="row">
            <div className="col">
                <img width={300} height={300 }src="https://www.pngitem.com/pimgs/m/27-278380_png-vector-pdf-adobe-acrobat-logo-png-transparent.png" alt="bb"/>
            </div>
            <div className="col">
            <img width={200} height={200} src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJUthvilyQpBtsol2eAxlg4-78wGkDqheWFg&usqp=CAU" alt="cc"/></div>
            <div className="col">
            <img width={300} height={300 } src="https://i.stack.imgur.com/9MYg8.png" alt="cc"/></div>
        </div>
        
        <div className="row">
            <div className="col">
        <h1 style={{display:"flex", color:"white",margin:"50px",backgroundColor:"rgb(189, 181, 181)",padding:"30px",alignItems:"center",border:"none solid black" ,borderRadius:"10px",fontWeight:"100",fontSize:"5",width:"100%",}}>The PDF files contain readings recorded from electric meters, and before the meters are sold, the company needs to check their validity.
             To do this, a series of electric tests are run on the meters, and the results are recorded in a PDF file. The PDF Extractor convert the data from the PDF file into a database, where other people can use the data to determine 
             the accuracy and validity of each electric meter.
        </h1>
            </div>
        </div>
    </div>
  <div>
  </div>
</div>
        </>
    )
}

export default Home
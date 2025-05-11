import { useState, useEffect } from "react";
import "bootstrap/dist/css/bootstrap.css";

function UpcomingDrive() {
  const [upcomingDrive, setUpcomingDrive] = useState([]);
  const [fetchDriveFlag, setFetchDriveFlag] = useState(true);

  function handleRefresh() {
    setFetchDriveFlag(false);
  }

  function HandleReportDownload() {
    fetch("http://localhost:8080/student/report")
      .then((response) => response.blob)
      .then((blob) => {
        const fileURL = window.URL.createObjectURL(new Blob(blob));
        let alink = document.createElement("a");
        alink.href = fileURL;
        alink.download = "Report.xlsx";
        alink.click();
      });
  }

  useEffect(() => {
    if (!fetchDriveFlag) {
      setFetchDriveFlag(true);
    }
    fetch("http://localhost:8080/drive")
      .then((res) => res.json())
      .then((data) => setUpcomingDrive(data));
  }, [fetchDriveFlag]);

  if (upcomingDrive.length === 0) {
    return (
      <>
        <h5>Upcoming Drives</h5>
        <p>No upcoming drives</p>
      </>
    );
  }
  return (
    <>
      <h5>Upcoming Drives</h5>
      {upcomingDrive.upcomingDrives.map((drive) => (
        <div className="card" key={drive.id}>
          <div className="card-body">
            <div className="">Name : {drive.vaccineName}</div>
            <div className="">Date : {drive.driveDate}</div>
            <div className="">Dose : {drive.availableDoses}</div>
          </div>
        </div>
      ))}
      <h5>Dashboard</h5>
      <p>Total Students : {upcomingDrive.totalStudent}</p>
      <p>Vaccinated Students : {upcomingDrive.vaccinatedStudent}</p>
      <p>Total % : {upcomingDrive.vaccinatedPercentage}</p>
      <button type="button" className="btn btn-primary" onClick={handleRefresh}>
        Refresh
      </button>{" "}
      <button
        type="button"
        className="btn btn-primary"
        onClick={HandleReportDownload}
      >
        Download Report
      </button>
    </>
  );
}

export default UpcomingDrive;

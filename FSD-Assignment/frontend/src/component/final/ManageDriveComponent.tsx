import { useEffect, useState } from "react";
import "./ManageDriveComponent.css";
function ManageDriveComponent() {
  const [drives, setDrives] = useState([]);
  const [editDriveId, setEditDriveId] = useState(-2);
  const [date, setDate] = useState("");
  const [name, setName] = useState("");
  const [id, setDriveId] = useState();
  const [errorMessage, setErrorMessage] = useState("");
  const [driveDate, setNewDriveDate] = useState("");
  const [vaccineName, setVaccinename] = useState("");
  const [doses, setAvailableDose] = useState("");

  function HandleCreateNewDrive() {
    SubmitNewDrive();
  }

  async function SubmitNewDrive() {
    setEditDriveId(-2);
    await fetch("http://localhost:8080/drive/create", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ driveDate, vaccineName, doses }),
    });
    setEditDriveId(-1);
  }

  async function getUpcomingDrive() {
    if (editDriveId !== -1) {
      return;
    }
    const response = await fetch("http://localhost:8080/drive/all");
    const data = await response.json();
    setDrives(data);
  }

  async function updateDriverDetails() {
    if (id != null) {
      await fetch("http://localhost:8080/drive/update", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ id, date, name }),
      });
      setEditDriveId(-1);
    } else {
      console.log("Not making 3rd party call as no change is made.");
    }
  }
  useEffect(() => {
    if (editDriveId === -2) {
      setEditDriveId(-1);
    }
    getUpcomingDrive();
  }, [editDriveId]);

  if (drives.length === 0) {
    return "No drive found";
  }

  function handleEdit(id: any) {
    setEditDriveId(id);
  }

  function HandleSubmit() {
    updateDriverDetails();
    console.log(JSON.stringify({ id, date, name }));
  }

  return (
    <div className="drive_management_body row">
      <div className="row g-3 align-items-center">
        <div className="col-auto">Drive Date:</div>{" "}
        <input
          type="date"
          className="form-control new_drive_data"
          required
          onChange={(event) => setNewDriveDate(event.target.value)}
        />
        <div className="col-auto">Vaccinae Name:</div>{" "}
        <input
          type="text"
          className="form-control new_drive_data"
          required
          onChange={(event) => setVaccinename(event.target.value)}
        />{" "}
        <input
          type="text"
          className="form-control new_drive_data"
          required
          onChange={(event) => setAvailableDose(event.target.value)}
        />
        <button
          type="button"
          className="btn btn-primary add_new_drive_data"
          onClick={HandleCreateNewDrive}
        >
          Add Drive
        </button>
        <div className="new_drive_error">{errorMessage}</div>
      </div>

      {drives.map((drive) => (
        <>
          {" "}
          {/*Show data*/}
          <div
            className="card drive_card"
            id={drive.id}
            key={drive.id}
            hidden={editDriveId === drive.id ? true : false}
          >
            <div className="card-body">
              <h5 className="card_title">Drive ID: {drive.id}</h5>
              <p className="card-text">Drive Date: {drive.driveDate}</p>
              Vaccine Name: {drive.vaccineName}
            </div>
            <button
              type="button"
              className="btn btn-primary"
              onClick={() => handleEdit(drive.id)}
            >
              Edit
            </button>
          </div>
          {/*Edit data*/}
          <div
            className="card drive_card"
            id={drive.id}
            key={drive.id + "edit"}
            hidden={editDriveId === drive.id ? false : true}
          >
            <div className="card-body">
              <input type="text" className="card_title" value={drive.id} />
              <input
                type="date"
                className="card_title"
                placeholder={drive.date}
                onChange={(event) => (
                  setDate(event.target.value), setDriveId(drive.id)
                )}
              />
              <input
                type="text"
                className="card_title"
                placeholder={drive.vaccineName}
                onChange={(event) => (
                  setName(event.target.value), setDriveId(drive.id)
                )}
              />
            </div>
            <button
              type="button"
              className="btn btn-primary"
              onClick={HandleSubmit}
            >
              Save
            </button>
          </div>
        </>
      ))}
      <></>
    </div>
  );
}

export default ManageDriveComponent;

import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

const AddClinicals = () => {
  const [patient, setPatient] = useState(null);
  const [componentName, setComponentName] = useState('');
  const [componentValue, setComponentValue] = useState('');
  const { patientId } = useParams();

  useEffect(() => {
    const fetchPatientDetails = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/patientservices/patients/${patientId}`);
        setPatient(response.data);
      } catch (error) {
        console.error('Error fetching patient details:', error);
      }
    };

    fetchPatientDetails();
  }, [patientId]);

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/patientservices/clinicaldata/clinicals', {
        "patientId":patientId,
        "componentName":componentName,
        "componentValue":componentValue
      });

      console.log('Data saved successfully:', response.data);
    } catch (error) {
      console.error('Error saving data:', error);
    }
  };

  return (
    <div>
      <h1>Add Clinical Data</h1>
      {patient && (
        <div>
          <h2>{patient.firstName} {patient.lastName}</h2>
          <p>Age: {patient.age}</p>
        </div>
      )}

      <form onSubmit={handleSubmit}>
        <label>
          Component Name:
          <input type="text" value={componentName} onChange={(e) => setComponentName(e.target.value)} />
        </label>
        <br />
        <label>
          Component Value:
          <input type="text" value={componentValue} onChange={(e) => setComponentValue(e.target.value)} />
        </label>
        <br />
        <button type="submit">Save</button>
      </form>
    </div>
  );
};

export default AddClinicals;

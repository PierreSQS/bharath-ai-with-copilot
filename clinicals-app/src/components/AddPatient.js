import React, { useState } from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';

const AddPatient = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [age, setAge] = useState('');

  const handleFirstNameChange = (e) => {
    setFirstName(e.target.value);
  };

  const handleLastNameChange = (e) => {
    setLastName(e.target.value);
  };

  const handleAgeChange = (e) => {
    setAge(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/patientservices/patients', {
        "firstName":firstName,
        "lastName":lastName,
        "age":age,
      });

      console.log('Patient saved:', response.data);
      toast.success('Patient saved successfully!');
      // Handle success or display a success message
    } catch (error) {
      console.error('Error saving patient:', error);
      // Handle error or display an error message
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h1>Add Patient</h1>
      <label>
        First Name:
        <input type="text" value={firstName} onChange={handleFirstNameChange} />
      </label>
      <br />
      <label>
        Last Name:
        <input type="text" value={lastName} onChange={handleLastNameChange} />
      </label>
      <br />
      <label>
        Age:
        <input type="text" value={age} onChange={handleAgeChange} />
      </label>
      <br />
      <button type="submit">Save Patient</button>
    </form>
  );
};

export default AddPatient;

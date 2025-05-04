import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [messages, setMessages] = useState();

  const [changedVal, setChangedVal] = useState('');

  useEffect(() => {


    const eventSource = new EventSource("http://localhost:8080/getStream");


    eventSource.onmessage = (event) => {

      const data1 = JSON.parse(event.data);
      setMessages(data1);

      console.log('data ', event.data);
      if ('LoanChanged' in data1) {
        setChangedVal('Loan');

      }
      if ('CommitmentChanged' in data1) {
        setChangedVal('Commitment');

      }
      if ('SecuritiesChanged' in data1) {
        setChangedVal('Securities');

      }



    };

    eventSource.onerror = (error) => {
      console.error("SSE error:", error);
      eventSource.close();
    };

    return () => {
      eventSource.close();
    };
  }, []);


  function getBackgroundColor(changedVal, val) {
    if (changedVal === val)

      return { backgroundColor: 'green' }
  }

  return (
    <div>
      <h1> Stream Data   </h1>


      <table style={{ border: '1px solid black', margin: '5px' }}>
        <tr>
          <th>Completed  </th> <th>In-Progress</th>  <th>Completed</th>
        </tr>
        <tr style={getBackgroundColor(changedVal, 'Loan')}>
          <td> Loan  </td>
          <td>  {messages && <p> {messages['Loan']['completed']}</p>}</td>
          <td>   {messages && <p>  {messages['Loan']['inProgress']}</p>} </td>
          <td> {messages && <p> {messages['Loan']['failed']}</p>}</td>
        </tr>
        <tr style={getBackgroundColor(changedVal, 'Commitment')}>
          <td> Commitment </td>
          <td>  {messages && <p> {messages['Commitment']['completed']}</p>}</td>
          <td>  {messages && <p>  {messages['Commitment']['inProgress']}</p>}</td>
          <td>  {messages && <p> {messages['Commitment']['failed']}</p>}</td>
        </tr>
        <tr style={getBackgroundColor(changedVal, 'Securities')}>
          <td> Securities  </td>
          <td> {messages && <p>   {messages['Securities']['completed']}</p>}</td>
          <td> {messages && <p> {messages['Securities']['inProgress']}</p>}</td>
          <td>{messages && <p>  {messages['Securities']['failed']}</p>}</td>
        </tr>
      </table>


    </div>
  );
}

export default App

const express = require('express');
const app = express();
const bodyParser = require('body-parser');

const users = [
    { username: 'root', password: '040902' },
    { username: 'user', password: '123456' }
];

// Parse JSON bodies
app.use(bodyParser.json());

// Serve static files from the current directory
app.use(express.static(__dirname));

app.get('/api/users', (req, res) => {
  // Logic for handling the GET request
  res.send('This is the users API endpoint');
});

app.post('/api/login', (req, res) => {
  // Get the username and password from the request body
  const { username, password } = req.body;

  console.log('Received login request');
  console.log('Username:', username);
  console.log('Password:', password);

  // Perform authentication logic
  // Connect to your SQL server and check if the provided username and password are valid
  // ...

  // Example logic for checking credentials (replace with your own logic)
  if (username === 'root' && password === '040902') {
    // Credentials are valid
    res.json({ connected: true, message: 'Login successful' });
  } else {
    // Credentials are invalid
    res.json({ connected: false, message: 'Invalid username or password' });
  }
});

const port = 3000; // or any other port you prefer
app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});



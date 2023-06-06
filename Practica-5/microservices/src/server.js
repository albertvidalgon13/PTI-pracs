const express = require('express')
const app = express()
const port = 8080
const fs = require('fs');

app.use(express.json());


app.post('/newrental', (req,res) => {
	if (!fs.existsSync('rentals.json')) {
		rentalsJSON = {"rentals": []};
	}
	else {
		rs = fs.readFileSync('rentals.json');
		aJSON = JSON.parse(rs);
	}
	fs.writeFileSync("rentals.json", JSON.stringify(rentalsJSON));
	rentalsJSON['rentals'].push(req.body);
	fs.writeFileSync("rentals.json", JSON.stringify(rentalsJSON));
	console.log(req.body);
	res.end();
})


app.post('/listrentals', (req,res) => {
	rs = fs.readFileSync('rentals.json');
	aJSON = JSON.parse(rs);
	console.log(aJSON);
	res.end();
})



app.listen(port, () => {
  console.log(`PTI HTTP Server listening at http://localhost:${port}`)
})


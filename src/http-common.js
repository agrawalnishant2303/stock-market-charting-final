import axios from "axios";

export default axios.create({
   baseURL: "https://stockmarketcharting-react.herokuapp.com/",
  //baseURL:"http://localhost:8080",
  headers: {
    "Content-type": "application/json"
  }
});
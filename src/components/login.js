import React, { Component } from "react";
import { Redirect } from "react-router-dom";

export default class Login extends Component {
    constructor(props) {
        super(props);
        this.handleLogin = this.handleLogin.bind(this);
        this.onChangeEmail = this.onChangeEmail.bind(this);
        this.onChangePassword = this.onChangePassword.bind(this);
    
        this.state = {
          email: "",
          password: "",
          user:[]
        };
      }
      onChangeEmail(e) {
        this.setState({
          email: e.target.value
        });
      }
    
      onChangePassword(e) {
        this.setState({
          password: e.target.value
        });
      }
      handleLogin(e) {
        e.preventDefault();
        if((this.state.email="admin") && (this.state.password="admin")){
          this.props.history.push("/LandingPage");
                    window.location.reload();
        }
        var data = {
            email:this.state.email,
            password:this.state.password
        }

        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer my-token',
                'My-Custom-Header': 'frontend',
            },
            body: JSON.stringify(data)
        };
        //fetch('https://stockmarketcharting.herokuapp.com/finduser', requestOptions)
        fetch('https://stockmarketcharting-react.herokuapp.com/finduser', requestOptions)
            .then(response => {
                this.setState({
                    user: response.data
                });
                if(this.state.user[0].confirmed == true){
                    this.props.history.push("/UserLandingPage");
                    window.location.reload();
                }
                console.log(response.data);
            })
            .catch(e => {
                console.log(e);
            });

      }

    render() {

        return (
          <div>
            <div className="col-md-12">
            <div className="card card-container">
            <form
            onSubmit={this.handleLogin}
            ref={c => {
              this.form = c;
            }}
          >
            <div className="form-group">
              <label htmlFor="username">Email</label>
              <input
                type="text"
                className="form-control"
                name="email"
                value={this.state.email}
                onChange={this.onChangeEmail}
              />
            </div>

            <div className="form-group">
              <label htmlFor="password">Password</label>
              <input
                type="password"
                className="form-control"
                name="password"
                value={this.state.password}
                onChange={this.onChangePassword}
              />
            </div>

            <div className="form-group">
              <button
                className="btn btn-primary btn-block">
                <span>Login</span>
              </button>
            </div>
          </form>
          </div>
          </div>
          </div>
        );
    }
}
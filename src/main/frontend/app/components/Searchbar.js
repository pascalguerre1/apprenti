import React, { Component } from 'react'
import './Searchbar.scss'
import BarIndexContainer from '../containers/BarIndexContainer'

export default class Searchbar extends Component {
    constructor(props){
        super(props)
        this.state = {
            searchInput: '',
            searchStringObj:{
                searchString:'' 
            }
        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(e) {
        this.setState({ searchInput: e.target.value})
    }

    searchBar (formPayload) {
        fetch(`/api/v1/bars/searchString`, {
        method: 'POST', 
        body: JSON.stringify(formPayload),
        headers: {
            Accept: 'application/json',
            'Content-Type': 'application/json'},
        credentials:"same-origin"       
        })
        location.assign("/bars/search-result")      
    }

    handleSubmit(e){
        e.preventDefault();
        let formPayload = this.state.searchStringObj
        formPayload.searchString = this.state.searchInput
        this.searchBar(formPayload)
    }

    render() {
        return (
                <form className="search-bar" onSubmit={this.handleSubmit}>
                    <div className="title">Find</div>
                    <input type="text" placeholder="Enter a bar | ...near a beach" onChange={this.handleChange}></input>
                    <button  type="submit" className="search-icon-box"> 
                    <i className="fas fa-search"></i>
                    </button>
                </form>
        )
    }
}


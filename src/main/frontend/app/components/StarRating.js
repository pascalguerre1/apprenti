import React, {Component} from "react"

const StarRating = props => {
    let starRating
    if(props.stars === 1){
        starRating = (
            <div>
                <i className="fa fa-star checked"></i>
                <i className="fa fa-star"></i>
                <i className="fa fa-star "></i>
                <i className="fa fa-star "></i>
                <i className="fa fa-star "></i>
            </div>)
    }
    if(props.stars === 2){
        starRating = (
            <div>
                <i className="fa fa-star checked"></i>
                <i className="fa fa-star checked"></i>
                <i className="fa fa-star "></i>
                <i className="fa fa-star "></i>
                <i className="fa fa-star "></i>
            </div>)
    }
    if(props.stars === 3){
        starRating = (
            <div>
                <i className="fa fa-star checked"></i>
                <i className="fa fa-star checked"></i>
                <i className="fa fa-star checked"></i>
                <i className="fa fa-star "></i>
                <i className="fa fa-star "></i>
            </div>)
    } 
    if(props.stars === 4){
        starRating = (
            <div>
                <i className="fa fa-star checked"></i>
                <i className="fa fa-star checked"></i>
                <i className="fa fa-star checked"></i>
                <i className="fa fa-star checked"></i>
                <i className="fa fa-star "></i>
            </div>)
    } 
    if( props.stars === 5){
        starRating = (
            <div>
                <i className="fa fa-star checked"></i>
                <i className="fa fa-star checked"></i>
                <i className="fa fa-star checked"></i>
                <i className="fa fa-star checked"></i>
                <i className="fa fa-star checked"></i>
            </div>)
    }
    return(
        <div>{starRating}</div>
    )        
}

export default StarRating
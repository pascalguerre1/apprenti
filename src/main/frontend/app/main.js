import React from "react"
import ReactDom from "react-dom"
import HomePageContainer from './containers/HomePageContainer'
import BarIndexContainer from './containers/BarIndexContainer'
import BarShowContainer from './containers/BarShowContainer'
import ReviewIndexContainer from './containers/ReviewIndexContainer'
import BarIndexCurrentUserContainer from './containers/BarIndexCurrentUserContainer'
import NavBar from './components/NavBar'
import BarIndexSearchContainer from './containers/BarIndexSearchContainer'

const containerMap = {
  "bar-index": BarIndexContainer,
  "review-index": ReviewIndexContainer,
  "bar-show": BarShowContainer,
  "home-index": HomePageContainer,
  "bar-index-currentUser": BarIndexCurrentUserContainer,
  "nav-bar": NavBar,
  "bar-index-search": BarIndexSearchContainer
}

for (const domId in containerMap) {
  if (document.getElementById(domId)) {
    const Component = containerMap[domId]
    ReactDom.render(<Component />, document.getElementById(domId))
  }
}
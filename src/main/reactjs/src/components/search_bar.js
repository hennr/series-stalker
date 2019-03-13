import React from "react";

export default function SearchBar(props) {
    return (
        <div className={"searchBar"}>
            {/*target.removeEventListener('click', myFunction, false);*/}
            <input id="searchInput" onChange={event => props.onSearch(event.target.value)}
            />
        </div>
    );
}

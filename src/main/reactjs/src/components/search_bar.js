import React from "react";

export default function SearchBar(props) {
    return (
        <div>
            <input id="searchInput" onChange={event => props.onSearch(event.target.value)}
            />
        </div>
    );
}

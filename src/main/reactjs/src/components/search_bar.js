import React from "react";

export default class SearchBar extends React.Component {
    render() {
        return (
            <div>
                <input autoFocus={true}
                       onChange={event => this.props.onSearch(event.target.value)}
                />
            </div>
        );
    }
}

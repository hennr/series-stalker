import React from 'react';

export default function Tile(props) {
    return (
        <div className="tile animated fadeIn">
            <span className="airdate">{props.series.nextAirDate}</span>
            <img src={props.series.imageUrl}/>
        </div>
    );
}

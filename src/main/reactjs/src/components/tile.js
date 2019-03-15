// @flow

import React from 'react';

type props = {
    series: series;
}

export type series = {
    nextAirDate: string,
    imageUrl: string
}

export default function Tile(props: props) {
    return (
        <div className="tile animated fadeIn">
            <span className="airdate">{props.series.nextAirDate}</span>
            <img src={props.series.imageUrl}/>
        </div>
    );
}

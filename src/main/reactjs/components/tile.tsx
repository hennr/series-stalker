import React from 'react';

interface Props {
    nextAirDate: string;
    imageUrl: string;
}

const Tile: React.FunctionComponent<Props> = (props) => {
    return <div className="tile animated fadeIn">
        <span className="airdate">{props.nextAirDate}</span>
        <img src={props.imageUrl} />
    </div>;
}

export default Tile;

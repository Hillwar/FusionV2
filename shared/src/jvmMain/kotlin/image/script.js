function onTextTap(state) {
    let newState = state;
    newState['$.text'] = 'New value';
    newState['$.color'] = '#FF0000';
    return newState;
}

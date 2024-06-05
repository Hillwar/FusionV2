function onSkuTap0(state, localState) {
    let newState = {...state};
    newState['$.selectedSku'] = 'CD Version';
    newState['$.color0'] = '#18181B';
    newState['$.color1'] = '#E6EAF0';
    newState['$.color2'] = '#E6EAF0';
    newState['$.price'] = '66 334';
    newState['$.url'] = 'https://ae04.alicdn.com/kf/S57df2f4985ad49f5b8d734577a2178b0h.jpg_650x650.jpg';
    return {state: newState, localState: localState};
}

function onSkuTap1(state, localState) {
    let newState = {...state};
    newState['$.selectedSku'] = 'PS5 Slim Digital';
    newState['$.color0'] = '#E6EAF0';
    newState['$.color1'] = '#18181B';
    newState['$.color2'] = '#E6EAF0';
    newState['$.price'] = '56 806';
    newState['$.url'] = 'https://ae04.alicdn.com/kf/S2b864cf62b17462aa382869ce020fa03s.jpg_640x640.jpg';
    return {state: newState, localState: localState};
}

function onSkuTap2(state, localState) {
    let newState = {...state};
    newState['$.selectedSku'] = 'PS5 Slim CD';
    newState['$.color0'] = '#E6EAF0';
    newState['$.color1'] = '#E6EAF0';
    newState['$.color2'] = '#18181B';
    newState['$.price'] = '61 586';
    newState['$.url'] = 'https://ae04.alicdn.com/kf/S57df2f4985ad49f5b8d734577a2178b0h.jpg_650x650.jpg';
    return {state: newState, localState: localState};
}

function appear(state, localState) {
    let index = state['$.selectedIndex'];
    let result;
    if (index === "0") {
        result = onSkuTap0(state, localState);
    } else if (index === "1") {
        result = onSkuTap1(state, localState);
    } else if (index === "2") {
        result = onSkuTap2(state, localState);
    }
    return result ? result : {state: state, localState: localState};
}

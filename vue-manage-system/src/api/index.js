import request from '../utils/request';

export const fetchData = query => {
    return request({
        url: 'http://localhost:3000/table.json',
        method: 'get',
        params: query
    });
};

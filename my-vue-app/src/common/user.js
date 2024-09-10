export const setLogStatus = (flag) => localStorage.setItem('logStatus', flag);
export const getLogStatus = () => localStorage.getItem('logStatus') || '';
export const removeLogStatus = () => localStorage.removeItem('logStatus');

export const setUserId = (id) => localStorage.setItem('userId', id);
export const getUserId = () => localStorage.getItem('userId') || '';
export const removeUserId = () => localStorage.removeItem('userId');

export const setUsername = (username) => localStorage.setItem('username', username);
export const getUsername = () => localStorage.getItem('username') || '';
export const removeUsername = () => localStorage.removeItem('username');

export const setSchoolId = (schoolId) => localStorage.setItem('schoolId', schoolId);
export const getSchoolId = () => localStorage.getItem('schoolId') || '';
export const removeSchoolId = () => localStorage.removeItem('schoolId');

export const setSex = (sex) => localStorage.setItem('sex', sex);
export const getSex = () => localStorage.getItem('sex') || '';
export const removeSex = () => localStorage.removeItem('sex');


import Cookies from 'js-cookie';
import API_URL from '../const';

const url = API_URL;

export const updateParent = async (parentData) => {
    const token = Cookies.get('jwtToken')
    try {
        const response = await fetch(`${url}/parents`, {
            method: 'PUT',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(parentData),
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.json();
        return {
            id: result.parent.parentID,
            name: `${result.parent.firstName} ${result.parent.lastName}`,
            email: result.parent.email,
            pesel : result.parent.pesel,
        }
    } catch (error) {
        console.error('Error updating teacher:', error);
        return null;
    }
};
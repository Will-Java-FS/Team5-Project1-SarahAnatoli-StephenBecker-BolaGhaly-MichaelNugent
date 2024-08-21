import { useEffect, useState } from "react";
import axiosInstance from "../../axios/AxiosInstance";

//CSS
import "../../CSS/AdminUser.css";

export const AdminUser = () => {
  const [allUsers, setAllUsers] = useState<any[]>([]);

  const getAllUsers = async () => {
    try {
      const response = await axiosInstance.get("/admin/users");

      if (response.status === 200) {
        setAllUsers(response.data);
      }
    } catch (error) {
      console.error("Failed to get all users.", error);
    }
  };

  const deleteUser = async (id: number) => {
    try {
      const response = await axiosInstance.delete(`/admin/user/${id}`);

      if (response.status === 200) {
        getAllUsers();
      }
    } catch (error) {
      console.error("Failed to get all users.", error);
    }
  }

  useEffect(() => {
    getAllUsers();
  }, []);

  return (
    <>
      <h2>Manage User Accounts</h2>
      <div>
        {allUsers.map((user, key) => (
          <div key={key} className="admin-user-card">
            <div className="admin-user-container">
              <div className="admin-user-info">
                <h6>Username</h6>
                <p>{user.username}</p>
              </div>
              <div className="admin-user-info">
                <h6>Email</h6>
                <p>{user.email}</p>
              </div>
              <div className="admin-user-info">
                <h6>First Name</h6>
                <p>{user.firstName == null ? " - " : user.firstName}</p>
              </div>
              <div className="admin-user-info">
                <h6>Last Name</h6>
                <p>{user.lastName == null ? " - " : user.lastName}</p>
              </div>
              <div className="admin-user-info">
                <h6>Age</h6>
                <p>{user.age == null ? " - " : user.age}</p>
              </div>
              <div className="admin-user-info">
                <h6>Weight</h6>
                <p>{user.weight == null ? " - " : user.weight}</p>
              </div>
              <div className="admin-user-info">
                <h6>Height</h6>
                <p>{user.height == null ? " - " : user.height}</p>
              </div>
              <div className="admin-user-info">
                <h6>Gender</h6>
                <p>{user.gender == null ? " - " : user.gender}</p>
              </div>
              <div className="admin-user-info">
                <h6>Daily Calorie Goal</h6>
                <p>
                  {user.dailyCalorieGoal == null
                    ? " - "
                    : user.dailyCalorieGoal}
                </p>
              </div>
            </div>
            <div className="admin-user-buttons">
              <button className="edit-button">
                <i className="fa-solid fa-pen-to-square" /> Edit
              </button>
              <button className="admin-button">
                <i className="fa-solid fa-user-tie" /> Make Admin
              </button>
              <button
                className="delete-button"
                onClick={() => deleteUser(user.id)}
              >
                <i className="fa-solid fa-trash" /> Delete
              </button>
            </div>
          </div>
        ))}
      </div>
    </>
  );
};

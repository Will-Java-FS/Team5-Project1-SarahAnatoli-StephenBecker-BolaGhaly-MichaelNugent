import { useEffect, useRef, useState } from "react";
import axiosInstance from "../../axios/AxiosInstance";

//CSS
import "../../CSS/AdminUser.css";

export const AdminUser = () => {
  const [allUsers, setAllUsers] = useState<any[]>([]);
  const [editMode, setEditMode] = useState(false);
  const [editUserId, setEditUserId] = useState<number>();
  const usernameRef = useRef<HTMLInputElement>(null);
  const emailRef = useRef<HTMLInputElement>(null);
  const firstNameRef = useRef<HTMLInputElement>(null);
  const lastNameRef = useRef<HTMLInputElement>(null);
  const ageRef = useRef<HTMLInputElement>(null);
  const weightRef = useRef<HTMLInputElement>(null);
  const heightRef = useRef<HTMLInputElement>(null);
  const genderRef = useRef<HTMLInputElement>(null);
  const dailyCalorieGoalRef = useRef<HTMLInputElement>(null);

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
      console.error("Failed to delete this user.", error);
    }
  };

  const makeUserAdmin = async (id: number) => {
    try {
      const response = await axiosInstance.post(`/admin/user/${id}/role`);

      if (response.status === 200) {
        getAllUsers();
      }
    } catch (error) {
      console.error("Failed to make this user an admin.", error);
    }
  };

  const editButtonClicked = async (id: number) => {
    setEditUserId(id);
    setEditMode(true);
  };

  const saveChangesForUserInfo = async (id: number) => {
    try {
      setEditMode(false);
      const response = await axiosInstance.patch(`/admin/user/${id}`, {
        username: usernameRef.current!.value,
        email: emailRef.current!.value,
        firstName: firstNameRef.current!.value,
        lastName: lastNameRef.current!.value,
        age: ageRef.current!.value,
        weight: weightRef.current!.value,
        height: heightRef.current!.value,
        gender: genderRef.current!.value,
        dailyCalorieGoal: dailyCalorieGoalRef.current!.value,
      });

      if (response.status === 200) {
        getAllUsers();
      }
    } catch (error) {
      console.error("Failed to update this user's information.", error);
    }
  };

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
                {editMode && editUserId === user.id ? (
                  <input
                    type="text"
                    defaultValue={user.username}
                    ref={usernameRef}
                  />
                ) : (
                  <p>{user.username}</p>
                )}
              </div>
              <div className="admin-user-info">
                <h6>Email</h6>
                {editMode && editUserId === user.id ? (
                  <input
                    type="email"
                    defaultValue={user.email}
                    ref={emailRef}
                  />
                ) : (
                  <p>{user.email}</p>
                )}
              </div>
              <div className="admin-user-info">
                <h6>First Name</h6>
                {editMode && editUserId === user.id ? (
                  <input
                    type="text"
                    defaultValue={user.firstName}
                    ref={firstNameRef}
                  />
                ) : (
                  <p>
                    {user.firstName && user.firstName.length > 0
                      ? user.firstName
                      : " - "}
                  </p>
                )}
              </div>
              <div className="admin-user-info">
                <h6>Last Name</h6>
                {editMode && editUserId === user.id ? (
                  <input
                    type="text"
                    defaultValue={user.lastName}
                    ref={lastNameRef}
                  />
                ) : (
                  <p>
                    {user.lastName && user.lastName.length > 0
                      ? user.lastName
                      : " - "}
                  </p>
                )}
              </div>
              <div className="admin-user-info">
                <h6>Age</h6>
                {editMode && editUserId === user.id ? (
                  <input
                    type="number"
                    defaultValue={user.age}
                    ref={ageRef}
                    min={0}
                  />
                ) : (
                  <p>{user.age == null ? " - " : user.age}</p>
                )}
              </div>
              <div className="admin-user-info">
                <h6>Weight</h6>
                {editMode && editUserId === user.id ? (
                  <input
                    type="number"
                    defaultValue={user.weight}
                    ref={weightRef}
                    min={0}
                    step={0.01}
                  />
                ) : (
                  <p>{user.weight == null ? " - " : user.weight}</p>
                )}
              </div>
              <div className="admin-user-info">
                <h6>Height</h6>
                {editMode && editUserId === user.id ? (
                  <input
                    type="number"
                    defaultValue={user.height}
                    ref={heightRef}
                    min={0}
                    step={0.01}
                  />
                ) : (
                  <p>{user.height == null ? " - " : user.height}</p>
                )}
              </div>
              <div className="admin-user-info">
                <h6>Gender</h6>
                {editMode && editUserId === user.id ? (
                  <input
                    type="text"
                    defaultValue={user.gender}
                    ref={genderRef}
                  />
                ) : (
                  <p>
                    {user.gender && user.gender.length > 0
                      ? user.gender
                      : " - "}
                  </p>
                )}
              </div>
              <div className="admin-user-info">
                <h6>Daily Calorie Goal</h6>
                {editMode && editUserId === user.id ? (
                  <input
                    type="number"
                    defaultValue={user.dailyCalorieGoal}
                    ref={dailyCalorieGoalRef}
                    min={0}
                  />
                ) : (
                  <p>
                    {user.dailyCalorieGoal == null
                      ? " - "
                      : user.dailyCalorieGoal}
                  </p>
                )}
              </div>
            </div>
            {editMode && editUserId === user.id ? (
              <div className="admin-user-buttons">
                <button
                  className="save-changes-button"
                  onClick={() => saveChangesForUserInfo(user.id)}
                >
                  <i className="fa-solid fa-check" /> Save Changes
                </button>
                <button
                  className="cancel-button"
                  onClick={() => setEditMode(false)}
                >
                  <i className="fa-solid fa-xmark" /> Cancel
                </button>
              </div>
            ) : (
              <div className="admin-user-buttons">
                <button
                  className="edit-button"
                  onClick={() => editButtonClicked(user.id)}
                >
                  <i className="fa-solid fa-pen-to-square" /> Edit
                </button>
                <button
                  className="admin-button"
                  onClick={() => makeUserAdmin(user.id)}
                >
                  <i className="fa-solid fa-user-tie" /> Make Admin
                </button>
                <button
                  className="delete-button"
                  onClick={() => deleteUser(user.id)}
                >
                  <i className="fa-solid fa-trash" /> Delete
                </button>
              </div>
            )}
          </div>
        ))}
      </div>
    </>
  );
};

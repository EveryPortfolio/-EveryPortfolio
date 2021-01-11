import React, { useCallback, useState } from 'react';

interface ProfileProps {
  username: string;
  name: string;
  onInsert?: (value: string) => void;
}

const Profile = ({ username, name, onInsert }: ProfileProps): React.ReactElement => {
  const [number, setNumber] = useState(0);
  const [value, setValue] = useState('');

  const onClick = useCallback(
    (e) => {
      if (onInsert) onInsert(value);
      setValue('');
      e.preventDefault();
    },
    [onInsert, value],
  );

  const onChange = useCallback((e) => {
    setValue(e.target.value);
  }, []);

  const onIncrease = useCallback(() => {
    setNumber(number + 1);
  }, [number]);

  const onDecrease = useCallback(() => {
    setNumber(number - 1);
  }, [number]);
  return (
    <>
      <div>
        <b>{username}</b>&nbsp;
        <span>{`(${name})`}</span>
      </div>
      <h2>{number}</h2>
      <div>
        <button type='button' onClick={onIncrease}>
          +1
        </button>
        <button type='button' onClick={onDecrease}>
          -1
        </button>
      </div>
      <form onSubmit={onClick}>
        <input type='text' placeholder='입력하세요.' value={value} onChange={onChange} />
        <button type='submit'>제출</button>
      </form>
    </>
  );
};

Profile.defaultProps = {
  onInsert: undefined,
};

export default Profile;

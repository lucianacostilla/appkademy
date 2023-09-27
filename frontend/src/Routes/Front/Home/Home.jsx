import React from 'react'
import Container from 'react-bootstrap/Container';
import { AnimatePresence, motion } from 'framer-motion';
import { useEffect, useState } from 'react';
import Filter from '../../../Components/Filter/Filter';
import CardProduct from '../../../Components/Card/Card';
import Search from '../../../Components/Search/Search';
import BannerProfes from '../../../assets/banner-profes.svg'
import './Home.css'
import { useAuth } from '../../../Context/AuthContext';

const Home = () => {
  const { isLoggedIn } = useAuth()
  const [likedTeachers, setLikedTeachers] = useState([])
  const [filtered, setFiltered] = useState([]);
  const [subjects, setSubjects] = useState([]);
  const [activeFilter, setActiveFilter] = useState('');
  const [teachingProficiency, setTeachingProficiency] = useState({
    subject: '',
  });
  const [searchData, setSearchData] = useState({
    pageNumber: 1,
    pageSize: 30,
  });
  const shuffleArray = (array) => {
    function compareRandom(a, b) {
      return Math.random() - 0.5;
    }
    return array.sort(compareRandom);
  }
  const fetchData = async () => {

    const postData = {
      pageNumber: searchData.pageNumber,
      pageSize: searchData.pageSize,
    };

    if (teachingProficiency.subject !== '') {
      postData.teachingProficiency = {
        subject: teachingProficiency.subject,
      };
    }
    try {
      const response = await fetch('http://localhost:8080/v1/categories/1/providers/search', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(postData),
      });
      if (response.ok) {
        const teachers = await response.json();
        let shuffledItems = []
        if (teachers.searchResults) {
          shuffledItems = shuffleArray(teachers.searchResults).slice(0, 9);
        }
        setFiltered(shuffledItems);
      } else {
        console.log(response)
      }
    } catch (error) {
      console.error('Error de red:', error);
    }
  }
  const getCategories = async () => {
    const postData = {
      pageNumber: 1,
      pageSize: 10,
    }
    try {
      const response = await fetch('http://localhost:8080/v1/categories/1/providers/teaching_subject/search', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(postData),
      });
      if (response.ok) {
        const subjects = await response.json();
        setSubjects(subjects.searchResults);
      } else {
        console.log(response)
      }
    } catch (error) {
      console.error('Error de red:', error);
    }
  };

  useEffect(() => {
    getCategories();
    fetchData();
  }, [activeFilter]);


  const getTeacherIds = async () => {
    if (isLoggedIn) {
      const userDataJSON = localStorage.getItem('user');
      const userData = JSON.parse(userDataJSON);
      const { token, userTypeId } = userData;
      const response = await fetch(`http://localhost:8080/v1/categories/1/customers/${userTypeId}`, {
        method: 'GET',
        mode: 'cors',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        }
      })
      if(response.ok){
        const data = await response.json();
        const likedTeachers = data.likedProviderIds;
        setLikedTeachers(likedTeachers)
      }
    } else {
      console.log('No se puede buscar ids de profes porque no esta logeado')
    }
  }

  useEffect(() => {
    getTeacherIds()
  }, [isLoggedIn])

  return (
    <main id='home'>
      <Container>
        <section className='container-main-banner'>
          <div className='banner-description'>
            <h1>Encuentra tu nueva pasión</h1>
            <Search categories={subjects} />
          </div>
        </section>

        <section>
          <div className='container-aprender'>
            <div>
              <h2>¿Qué te gustaría aprender hoy?</h2>
              <p>Te dejamos los tags para que encuentres los profesores recomendados!</p>
            </div>
          </div>

          <Filter
            categories={subjects}
            setSearchData={setSearchData}
            activeFilter={activeFilter}
            setActiveFilter={setActiveFilter}
            setTeachingProficiency={setTeachingProficiency}
          />
          <motion.div
            layout
            className="grid-container"
          >
            <AnimatePresence>
              {filtered.length > 0 ? (
                filtered.map(teacher => (
                  <div key={teacher.id}>
                    <CardProduct
                      teacher={teacher}
                      ids={likedTeachers}
                    />
                  </div>
                ))
              ) : (
                <p>No se encontraron resultados.</p>
              )}
            </AnimatePresence>
          </motion.div>
        </section>

        <section className='container-profes'>
          <img src={BannerProfes} alt="Appkademy banner profesores" />
          <div>
            <h3>Quieres unirte al equipo de profes?</h3>
            <p>Envianos un mail a profesores@appkademy.com</p>
          </div>
        </section>
      </Container>
    </main>
  )
}

export default Home
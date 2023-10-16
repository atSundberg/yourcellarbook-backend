// window.addEventListener('scroll', () => {
//     const section1 = document.getElementById('section1');
//     const section2 = document.getElementById('section2');

//     console.log('Scroll event triggered');
//     console.log('window.scrollY:', window.scrollY);
//     console.log('section1.offsetHeight:', section1.offsetHeight);

//     if (window.scrollY >= section1.offsetHeight) {
//         console.log('Changing background color');
//         section2.style.backgroundColor = '#3498db'; // Change to the desired background color
//     } else {
//         console.log('Resetting background color');
//         section2.style.backgroundColor = '#e74c3c'; // Initial background color for section 2
//     }
// });

document.addEventListener('alpine:init', () => {
  Alpine.data('account', () => ({
    wines: [],
    producers: [],
    regions: [],
    grapes: [],
    client: null,
    isSignedUp: false,
    isSignedIn: false,
    isAdminUser: false,
    selectedPage: 'home',
    showNav: false,
    isTransitioning: false,
    isTransitioning2: false,
    modalTop: '50%', // Initial top position

    // Function to update the modal's position
    updateModalPosition() {
      const modal = document.querySelector('.modal')

      if (modal) {
        // Calculate the new top position based on scroll
        const scrollTop = window.scrollY
        const modalHeight = modal.clientHeight
        const windowHeight = window.innerHeight
        const newTop = `${(windowHeight - modalHeight) / 2 + scrollTop}px`

        // Update the modal's top position

        this.modalTop = newTop
      }
    },

    // Listen for scroll events and update modal position
    listenForScroll() {
        console.log("listening for scroll")
      window.addEventListener('scroll', this.updateModalPosition)
    },

  
    myFunction() {
      // Your function logic here
      console.log("modal: ", document.querySelector('.modalSU'))
      console.log(window.scrollY);
      this.updateModalPosition()
      console.log('Button clicked')
      this.accountModal.userFlow = 'signUp'
      console.log(this.accountModal.userFlow)
    },
    accountModal: {
      userFlow: '',
      open: 'login',
    },
    formData: {
      firstName: null,
      lastName: null,
      email: null,
      password: null,
      passwordConfirm: null,
    },
    formMessage: {
      loginMessage: null,
      signupMessage: null,
    },
    wineList: {
      filter: '',
      filteredWines: [],
    },
    grapePicker: {
      grapes: [],
      filteredGrapes: [],
      grapeFilter: '',
      selectedGrape: {},
      active: 0,
    },
    regionPicker: {
      open: true,
      filteredRegions: [],
      filter: '',
      toggleDropdown() {
        this.open = !this.open
      },
    },
    drinkWineModal: {
      open: false,
      wine: {},
      formData: {
        date: null,
        amount: 1,
        rating: 0,
        verdict: null,
      },
    },
    editWineModal: {
      open: false,
      wine: null,
      formData: {
        user_id: null,
        name: null,
        vintage: null,
        producer: null,
        region: null,
        country: null,
        grapes: [],
        category: null,
        quantity: null,
        information: null,
        purchase_price: null,
      },
    },
    addWineForm: {
      user_id: null,
      name: null,
      vintage: null,
      producer: null,
      region: null,
      region_id: null,
      country: null,
      grapes: [],
      category: null,
      quantity: null,
      details: {
        information: null,
        purchase_price: null,
      },
      formLoading: true,
    },
    chat: {
      question: '',
      answer: '',
      foodAnswer: '',
      modalOpen: false,
    },

    handleSelectRegion(region) {
      console.log('handleSelectRegion: ', region)
      this.addWineForm.region_id = region.id
      this.addWineForm.region = region.region_name
      this.regionPicker.filter = region.region_name
      // this.regionPicker.filter = ''
      this.addWineForm.country = region.country
      this.regionPicker.toggleDropdown()
    },
    filterGrapes() {
      // console.log('filterGrapes.start', this.grapePicker.grapeFilter)
      filter = this.grapePicker?.grapeFilter?.toLowerCase()
      this.grapePicker.filteredGrapes = this.filterObjects(filter, this.grapes, ['name'])
      // console.log('shows', this.grapepicker?.filteredGrapes)
    },
    filterRegions() {
      filter = this.regionPicker?.filter?.toLowerCase()
      this.regionPicker.filteredRegions = this.filterObjects(filter, this.regions, ['region_name', 'country'])
    },

    //   async getGrapes() {
    //     if (this.grapePicker.grapes.length > 0) {
    //       return this.grapePicker.grapes
    //     }
    //     this.grapePicker.grapes = await this.client.collection('grapes').getFullList(200, { sort: 'name' })
    //     console.log('getGrapes.grapes', this.grapePicker.grapes)
    //   },
    initFilteredWines() {
        console.log("initFilteredWines")
      this.filterWinesStart()
    },

    filterObjects(filterString, objects, criteria) {
      // console.log('filterObjects', filterString, objects, criteria)
      const searchFilter = filterString.toLowerCase()
      return objects.filter((object) => {
        return criteria.some((criterion) => {
          return object[criterion].toLowerCase().indexOf(searchFilter) >= 0
        })
      })
    },

    async filterWinesStart() {
      // console.log('filterWinesStart.startValue', this.wineList.filter)
      filter = this?.wineList?.filter?.toLowerCase()
      // this.wineList.filteredWines = this.wines.filter((wine) => this?.isAcceptedWine(filter, wine))
      this.wineList.filteredWines = this.filterObjects(filter, this.wines, ['name', 'producer', 'region', 'country'])
    },

    filterWines() {
      filter = this.wineList.filter.toLowerCase()
      this.wineList.filteredWines = this.filterObjects(filter, this.wines, ['name', 'producer', 'region', 'country'])
      this.sortListDefault()
    },

    sortListDefault() {
      this.wineList.filteredWines.sort((a, b) => {
        return new Date(b.created) - new Date(a.created)
      })
    },

    addGrapeToSelection(grapeToAdd) {
      if (!this.addWineForm.grapes.includes(grapeToAdd)) {
        this.addWineForm.grapes.push(grapeToAdd)
      }
      this.grapePicker.grapeFilter = ''
    },
    removeSelectedGrape(grapeToRemove) {
      console.log('removeSelectedGrape', grapeToRemove)
      this.addWineForm.grapes = this.addWineForm.grapes.filter((grape) => {
        return grape.name != grapeToRemove.name
      })
    },
    //   handleKeyboardEvent(e) {
    //       console.log('active: ', this.grapePicker.active,)
    //       if (e?.keyCode == 40 && this.grapePicker.active <= this.grapePicker.filteredGrapes.length) {
    //           this.grapePicker.active += 1;
    //         } else if (e?.keyCode == 38 && this.grapePicker.active > -1) {
    //             this.grapePicker.active -= 1;
    //         } else if (e?.keyCode == 13) {
    //             this.addSelectedGrape();
    //         }
    //   },
    //   addSelectedGrape() {
    //     if (this.grapePicker.active > -1 && this.grapePicker.active <= this.grapePicker.filteredGrapes.length) {
    //         this.addGrapeToSelection(this.grapePicker.filteredGrapes[this.grapePicker.active])
    //     }
    //   },

    getPbClient() {
      if (!this.client) {
        this.client = new PocketBase('http://127.0.0.1:8090')
        // this.client = new PocketBase('https://your-cellar-book.fly.dev/')
      }
      return this.client
    },

    async init() {
      // console.log('Pocketbase auth', window.localStorage.getItem('pocketbase_auth'))
      window.localStorage.clear()
      this.getPbClient()
      this.listenForScroll()

      // capture invalid token
      this.client.afterSend = function (response, data) {
        if (response.status === 401) {
          return
        }
        return data
      }
      // if user is not logged in, show login / signup page
      if (!window.localStorage.getItem('pocketbase_auth')) {
        return
      }
      const auth = JSON.parse(window.localStorage.getItem('pocketbase_auth'))
      this.client.authStore.save(auth.token, auth.model)
      // fetch wines in cellar
      // this.getCellar()

      if (this.wines) {
        this.isSignedIn = true
      }

      // // suscribe to live update events
      this.subscribeToWines()
    },

    async getOneById(id) {
      const wine = await this.getPbClient().collection('wines').getOne(id, { expand: 'producer, region, grapes' })
      wine['region'] = wine.expand.region.region_name
      wine['country'] = wine.expand.region.country
      wine['producer'] = wine.expand.producer.name
      wine['grapes'] = wine.expand.grapes
      console.log('wineAdded: ', wine)
      return wine
    },

    async subscribeToWines() {
      this.client.collection('wines').subscribe('*', async (event) => {
        if (event.action === 'create') {
          wine = await this.getOneById(event.record.id)

          this.wines.push(wine)
          this.filterWines()
          console.log('subscribe.event.record', event.record, event.action)
        } else if (event.action === 'delete') {
          this.wines.filter((wine) => wine.id !== event.record.id)
        }
      })
    },

    async loginBtn() {
      this.formData.email = 'master@ycb.com'
      this.formData.password = 'masterycb'
      await this.login()
    },
    //       // after the above you can also access the auth data from the authStore
    //       console.log(this.getPbClient().authStore.isValid)
    //       console.log(this.getPbClient().authStore.token)
    //       console.log(this.getPbClient().authStore.model.id)

    async logout() {
      this.client.authStore.clear()
      this.isSignedIn = false
      this.selectedPage = 'home'
    },

    async login() {
      try {
        const user = await this.getPbClient()
          .collection('users')
          .authWithPassword(this.formData.email, this.formData.password)

        console.log("user: ", user);

        //   console.log(user)
        this.wines = await this.getCellar()
        this.producers = await this.fetchProducers()
        this.regions = await this.fetchRegions()
        this.grapes = await this.fetchGrapes()

          console.log('login.getCellar.wines', this.wines)
          console.log('login.getCellar.PROD', this.producers)
          console.log('login.getCellar.REG', this.regions)
          console.log('login.getCellar.Grapes', this.grapes)

        this.isSignedIn = true
        this.accountModal.userFlow = ''
        this.formData.email = ''
        this.formData.password = ''

        this.showNav = false

        if (this.wines) {
          this.initFilteredWines()
          await this.subscribeToWines()
        }
      } catch (err) {
        this.formMessage.loginMessage = err.data.message
        console.log(err)
      }
    },

    async signup() {
      try {
        console.log('signup().start')
        const data = {
          emailVisibility: false,
          email: this.formData.email,
          password: this.formData.password,
          passwordConfirm: this.formData.passwordConfirm,
          name: this.formData.firstName + ' ' + this.formData.lastName,
          role: 'base',
        }
        console.log('signup().data', data)

        const record = await this.getPbClient().collection('users').create(data)

        await this.getPbClient().collection('users').requestVerification(this.formData.email)

        this.formData.email = ''
        this.formData.password = ''
        this.formData.passwordConfirm = ''
        this.formMessage.signupMessage =
          'Successfully signed up! \nPlease check your mailbox to verify the email-address.'
        this.isSignedUp = true
      } catch (err) {
        console.log('signup_error: ', err)
        this.formMessage.signupMessage = 'Something went wrong, try again.'
      }
    },

    getGrapeIds(grapes) {
      ids = []
      grapes.forEach((grape) => {
        ids.push(grape.id)
      })
      return ids
    },

    async addOrGetProducer(producerName) {
      producerId = ''
      this.producers.forEach((producer) => {
        // console.log('producer:::', producer, producerName, (producer.name == producerName))
        if (producerName.toLowerCase() == producer.name.toLowerCase()) {
          producerId = producer.id
        }
      })
      if (producerId != '') {
        return producerId
      }

      const data = {
        name: producerName,
      }

      try {
        const record = await this.getPbClient().collection('producers').create(data)
        console.log('Producer record: ', record)
        return record.id
      } catch (e) {
        console.log('Error adding producer: ', e)
      }
    },

    async addOrGetRegion(regionName, country) {
      regionId = ''
      this.regions.forEach((region) => {
        if (
          regionName.toLowerCase() == region.region_name.toLowerCase() &&
          country.toLowerCase() == region.country.toLowerCase()
        ) {
          regionId = region.id
        }
      })

      if (regionId != '') {
        return regionId
      }

      const data = {
        region_name: regionName,
        country: country,
      }

      try {
        const record = await this.getPbClient().collection('regions').create(data)

        console.log('Region record: ', record)
        return record.id
      } catch (e) {
        console.log('Error adding region: ', e)
      }
    },

    async doAddWine() {
      producer = await this.addOrGetProducer(this.addWineForm.producer)
      // region = await this.addOrGetRegion(this.addWineForm.region, this.addWineForm.country)
      console.log('doAddWine().ID', this.getPbClient().authStore.model.id)
      console.log('doAttWine.FORM:', this.addWineForm)

      data = {
        userID: this.getPbClient().authStore.model.id,
        name: this.addWineForm.name,
        vintage: this.addWineForm.vintage,
        producer: producer,
        region: this.addWineForm.region_id,
        quantity: this.addWineForm.quantity,
        grapes: this.getGrapeIds(this.addWineForm.grapes),
        information: this.addWineForm.details.information,
        category: this.addWineForm.category?.toLowerCase(),
        purchase_price: this.addWineForm.details.purchase_price,
        is_finished: false,
        storing_location: this.addWineForm.storing_location,
      }

      try {
        const record = await this.getPbClient().collection('wines').create(data)
        this.selectedPage = 'home'
        console.log('record: ', record)
      } catch (e) {
        console.log('error: ', e)
      }
    },

    getParsedWineData(wine) {
      data = {
        userID: wine.userID,
        name: wine.name,
        vintage: wine.vintage,
        producer: wine.expand.producer.id,
        region: wine.expand.region.id,
        category: wine.category?.toLowerCase(),
        quantity: wine.quantity,
        grapes: this.getGrapeIds(wine.grapes),
        storing_location: wine.storing_location,
        is_finished: wine.is_finished,
        information: wine.information,
        purchase_price: wine.purchase_price,
      }

      return data
    },

    async updateWine() {
      console.log('updateWine.start', this.editWineModal)
      data = this.getParsedWineData(this.editWineModal.wine)
      // data = {
      //   userID: this.editWineModal.wine.userID,
      //   name: this.editWineModal.wine.name,
      //   vintage: this.editWineModal.wine.vintage,
      //   producer: this.editWineModal.wine.expand.producer.id,
      //   region: this.editWineModal.wine.expand.region.id,
      //   category: this.editWineModal.wine.category?.toLowerCase(),
      //   quantity: this.editWineModal.wine.quantity,
      //   grapes: this.getGrapeIds(this.editWineModal.wine.grapes),
      //   storing_location: this.editWineModal.wine.storing_location,
      //   is_finished: this.editWineModal.wine.is_finished,
      //   information: this.editWineModal.wine.information,
      //   purchase_price: this.editWineModal.wine.purchase_price,
      // }

      for (const [key, value] of Object.entries(this.editWineModal.formData)) {
        if (value != null && key != 'grapes') {
          console.log(key, ':', value)
          data[key] = value
        }
      }
      console.log('data: ', data)
      console.log('wine:', this.editWineModal.wine)
      try {
        const record = await this.getPbClient().collection('wines').update(this.editWineModal.wine.id, data)
        this.selectedPage = 'home'
        console.log('record: ', record)
      } catch (e) {
        console.log('error: ', e)
      }
    },

    async addWineToHistory(wineID, wineData) {
      console.log('addWineToHistory.wineID: ', wineID)
      console.log('addWineToHistory.wineData: ', wineData)

      const data = {
        userID: this.getPbClient().authStore.model.id,
        wine: wineID,
        consumed: wineData.date,
        rating: wineData.rating?.toLowerCase(),
        verdict: wineData.verdict,
      }

      const record = await this.getPbClient().collection('historic_wines').create(data)
      console.log('addWineToHistory.record: ', record)
    },
    async edit_quantity_in_collection(wineID, quantityLeft) {
      // make put-request to edit the quantity of the wine
      console.log('edit_quantity_in_collection.start')
      const record = await this.getPbClient().collection('wines').update(wineID, { quantity: quantityLeft })
      console.log('edit_quantity_in_collection.record', record)
    },
    async markWineAsFinished(wineID) {
      console.log('markWineAsFinished.start')
      const record = await this.getPbClient().collection('wines').update(wineID, { is_finished: true })
      console.log('markWineAsFinished.record', record)
    },

    drinkWine() {
      console.log('drinkWine.start', this.drinkWineModal)
      data = this.getParsedWineData(this.drinkWineModal.wine)
      const quantityLeft = data.quantity - this.drinkWineModal.formData.amount

      if (quantityLeft <= 0) {
        this.markWineAsFinished(this.drinkWineModal.wine.id)
      }
      this.edit_quantity_in_collection(this.drinkWineModal.wine.id, quantityLeft)
      this.addWineToHistory(this.drinkWineModal.wine.id, this.drinkWineModal.formData)
    },

    async getCellar() {
      // listOfWines = await this.client.collection('wines').getFullList(200, { sort: 'created' })
      listOfWines = await this.client
        .collection('wines')
        .getFullList(1000, { sort: '-created', expand: 'region, producer, grapes' })

      listOfWines.map((wine) => {
        wine['region_name'] = wine.expand.region.region_name
        wine['country'] = wine.expand.region.country
        wine['producer'] = wine.expand.producer.name
        wine['grapes'] = wine.expand.grapes
      })
      return listOfWines
    },
    async fetchProducers() {
      if (this.producers.length > 0) {
        return this.producers
      }
      const producers = await this.getPbClient().collection('producers').getFullList(1000, { sort: 'name' })
      return producers
    },
    async fetchRegions() {
      if (this.regions.length > 0) {
        return this.regions
      }
      const regions = await this.getPbClient().collection('regions').getFullList(1000, { sort: 'region_name' })
      return regions
    },
    async fetchGrapes() {
      if (this.grapes.length > 0) {
        return this.grapes
      }
      const grapes = await this.getPbClient().collection('grapes').getFullList(1000, { sort: 'name' })
      return grapes
    },

    getStatus() {
      console.log('status: ', this.isSignedIn)
      if (this.isSignedIn) {
        return true
      }
      return false
    },

    getWineString(wine) {
      return wine.name + ', ' + wine.producer + ', ' + wine.region_name + ', ' + wine.vintage + ';'
    },

    getWineList() {
      allWines = []
      this.wines.map((wine) => {
        allWines.push(this.getWineString(wine))
      })
      return allWines
    },

    handleWineQuestion() {
      console.log('handleWineQuestion.start')
      fetch('http://127.0.0.1:5000/chat', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          text: this.chat.question,
        }),
      })
        .then((response) => response.json())
        .then((data) => {
          this.chat.answer = data.response
          console.log(data)
        })
        .catch((error) => {
          console.error(error)
        })
    },
    getWineSuggestion() {
      console.log('getWineSuggestion.start')
      fetch('http://127.0.0.1:5000/wine/suggestion/', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          wines: this.getWineList(),
          dish: 'risotto',
        }),
      })
        .then((response) => response.json())
        .then((data) => {
          this.chat.answer = data.response
          console.log(data)
        })
        .catch((error) => {
          console.error(error)
        })
    },
    getFoodSuggestion(wine) {
      console.log('getFoodSuggestion.start:::', wine)
      fetch('http://127.0.0.1:5000/food/suggestion', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          wine: this.getWineString(wine),
        }),
      })
        .then((response) => response.json())
        .then((data) => {
          this.chat.foodAnswer = data.response
          this.chat.modalOpen = true
          console.log(data)
        })
        .catch((error) => {
          console.error(error)
        })
    },
  }))
})

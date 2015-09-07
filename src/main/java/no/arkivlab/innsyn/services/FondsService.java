package no.arkivlab.innsyn.services;
import javax.transaction.Transactional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import no.arkivlab.innsyn.models.n5.Fonds;
import no.arkivlab.innsyn.operations.iface.IFondsService;
import no.arkivlab.innsyn.repositories.IFondsRepository;

@Transactional
@Service
public class FondsService implements IFondsService {
	 		
		@Autowired
		IFondsRepository fondsRepository;

		@Override
		public List<Fonds> getAllFonds() {			
			return fondsRepository.getAllFonds();
		}

		@Override
		public Fonds getFondsBySystemId(String systemId) {
			return fondsRepository.findBySystemId(systemId);
		}

		@Override
		public List<Fonds> getFondsByCreatedDate(String createdDate) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Fonds getFondsById(Long id) {
			// TODO Auto-generated method stub
			return null;
		}
}

SHELL=/bin/bash
dcd:
	cd .. && make dcd
dcup-full-action:
	cd .. && make dcup-full-action
deck-update-to-3:
	deck convert --from kong-gateway-2.x --to kong-gateway-3.x
install-deck-mac-os:
	brew tap kong/deck
	brew install deck
install-deck-linux:
	curl -sL https://github.com/kong/deck/releases/download/v1.15.0/deck_1.15.0_linux_amd64.tar.gz -o deck.tar.gz
	tar -xf deck.tar.gz -C /tmp
	sudo cp /tmp/deck /usr/local/bin/
kong-config:
	#TODO: create Kong config
